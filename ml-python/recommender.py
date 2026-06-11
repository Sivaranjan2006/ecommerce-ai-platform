from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

app = FastAPI(title="E-Commerce Recommendation Engine API")

# Catalog dataset for content-based filtering vectorization
mock_products = [
    {"product_id": 101, "name": "Wireless Gaming Mouse", "description": "High DPI RGB wireless mouse for gaming"},
    {"product_id": 102, "name": "Mechanical Keyboard", "description": "RGB backlit mechanical keyboard with blue switches"},
    {"product_id": 103, "name": "Ergonomic Desk Chair", "description": "Comfortable mesh chair for long office hours"},
    {"product_id": 104, "name": "Gaming Monitor", "description": "27-inch 144Hz monitor with vivid colors for gamers"},
    {"product_id": 105, "name": "Leather Journal", "description": "Premium leather notebook for office writing"}
]

# Initialize DataFrame and compute TF-IDF matrix from text descriptions
df = pd.DataFrame(mock_products)
tfidf = TfidfVectorizer(stop_words='english')
tfidf_matrix = tfidf.fit_transform(df['description'])

# Compute global pairwise cosine similarity scores
cosine_sim = cosine_similarity(tfidf_matrix, tfidf_matrix)

def get_recommendations(target_product_id: int, top_n: int = 2) -> list:
    """
    Computes text similarity vectors using Cosine Similarity to find 
    the top N closest matches based on product descriptions.
    """
    if target_product_id not in df['product_id'].values:
        return []
        
    # Extract index of the target item and sort similarity scores
    idx = df[df['product_id'] == target_product_id].index[0]
    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    
    # Exclude the target product itself and slice top N results
    sim_scores = sim_scores[1:top_n+1]
    
    product_indices = [i[0] for i in sim_scores]
    return df.iloc[product_indices][['product_id', 'name']].to_dict(orient='records')

class RecommendationRequest(BaseModel):
    product_id: int
    num_recommendations: int = 2

@app.post("/api/recommend")
def recommend_products(request: RecommendationRequest):
    """
    Inbound POST route interacting with external Spring Boot microservices 
    to deliver real-time recommendation data payloads.
    """
    predictions = get_recommendations(request.product_id, request.num_recommendations)
    
    if not predictions:
        raise HTTPException(status_code=404, detail="Product ID not found")
        
    return {
        "target_product_id": request.product_id, 
        "recommendations": predictions
    }