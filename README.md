<!DOCTYPE html>
<html lang="en">
<body>
    <h1>Inventory Management System</h1>
    <p>This Inventory Management System is built using Spring REST API, designed to efficiently manage and track inventory for a business.</p>
    <h2>Features</h2>
    <ul>
        <li><strong>CRUD Operations</strong>: Perform Create, Read, Update, and Delete operations on inventory items.</li>
        <li><strong>Search and Filter</strong>: Easily search and filter inventory items based on various criteria.</li>
        <li><strong>Stock Tracking</strong>: Keep track of stock quantities and receive notifications for low stock levels.</li>
        <li><strong>RESTful API</strong>: Built using Spring REST, making it easy to integrate with other systems and applications.</li>
    </ul>
    <h2>Technologies Used</h2>
    <ul>
        <li><strong>Java</strong>: Programming language for the backend logic.</li>
        <li><strong>Spring Boot</strong>: Framework for creating RESTful APIs.</li>
        <li><strong>Spring Data JPA</strong>: Simplifies data access for the database.</li>
        <li><strong>MySQL</strong>: Database to store inventory data.</li>
    </ul>
    <h2>Setup Instructions</h2>
    <h3>Prerequisites</h3>
    <ul>
        <li>Java Development Kit (JDK) installed</li>
        <li>Maven installed</li>
        <li>MySQL installed</li>
    </ul>
    <h3>Steps to Run</h3>
    <ol>
        <li>Clone the repository:</li>
    </ol>
    <pre><code>git clone https://github.com/yourusername/inventory-management.git</code></pre>
    <ol start="2">
        <li>Navigate to the project directory:</li>
    </ol>
    <pre><code>cd inventory-management</code></pre>
    <ol start="3">
        <li>Update <code>application.properties</code> with your MySQL configuration:</li>
    </ol>
    <pre><code>spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=your_username
spring.datasource.password=your_password</code></pre>
    <ol start="4">
        <li>Run the application using Maven:</li>
    </ol>
    <pre><code>mvn spring-boot:run</code></pre>
    <ol start="5">
        <li>Once the application is running, you can access the API documentation:</li>
    </ol>
    <pre><code>http://localhost:8080/</code></pre>
    <h2>API Endpoints</h2>
    <h3>Inventory Items</h3>
    <ul>
        <li><code>GET /ims/products</code>: Get all inventory items</li>
        <li><code>GET /ims/products/{id}</code>: Get item by ID</li>
        <li><code>POST /ims/products</code>: Create a new item</li>
        <li><code>PUT /ims/products/{id}</code>: Update an existing item</li>
        <li><code>DELETE /ims/products/{id}</code>: Delete an item</li>
    </ul>
    <h2>Contributing</h2>
    <p>Contributions are welcome! If you have suggestions for improvements or new features, please open an issue or a pull request.</p>
</body>
</html>
