# ML-Powered E-Commerce Platform

A full-stack, microservice-based e-commerce platform demonstrating service-to-service communication between a core backend and an external machine learning engine.

## 🚀 Architecture & Tech Stack

* **Core Backend:** Java 21, Spring Boot, Spring Web, Spring Data JPA, Hibernate
* **Machine Learning Microservice:** Python, FastAPI, Pandas, Scikit-learn (TF-IDF, Cosine Similarity)
* **Database:** MySQL (Hosted on Aiven Cloud)
* **Frontend:** HTML5, Vanilla JavaScript (Fetch API), Bootstrap 5
* **Deployment:** Render (Backend APIs), Vercel (Frontend Store)

## ⚙️ Features

* **RESTful API:** Robust Java backend managing the product catalog and routing external requests.
* **Content-Based Filtering:** Python engine calculates cosine similarity vectors across product descriptions to serve real-time recommendations.
* **Fault Tolerance:** Built-in fallback mechanisms ensure the primary storefront remains operational even if the external ML microservice experiences downtime.
* **Cloud-Native:** Configured with environment variables and secure CORS policies for distributed cloud deployment.

## 💻 Local Development

### Prerequisites
* Java 21+
* Python 3.9+
* MySQL Database

### Running the Backend (Java)
1. Navigate to the `backend-java` directory.
2. Update `application.properties` with your local database credentials or set the required environment variables (`DB_URL`, `DB_USER`, `DB_PASSWORD`).
3. Run the Spring Boot application on port `8080`.

### Running the ML Engine (Python)
1. Navigate to the `ml-python` directory.
2. Install dependencies: `pip install fastapi uvicorn pandas scikit-learn`
3. Start the server: `uvicorn main:app --reload --port 8000`

### Running the Frontend
1. Open `frontend/product.html` in any modern web browser or run it via a local live server.
