const BASE_URL = "http://localhost:8080";
let currentProduct = null;

// TAB SWITCHING
function showSection(sectionId) {
    document.getElementById("onboardSection").classList.add("hidden");
    document.getElementById("predictSection").classList.add("hidden");

    document.getElementById(sectionId).classList.remove("hidden");
}

// ================= FLOW 1 =================

// Fetch product preview
function fetchProduct() {
    const url = document.getElementById("productUrl").value;

    fetch(`${BASE_URL}/api/products/preview`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ url: url })
    })
    .then(res => res.json())
    .then(data => {
        currentProduct = data;

        document.getElementById("productCard").classList.remove("hidden");
        document.getElementById("image").src = data.imageUrl || "";
        document.getElementById("name").innerText = data.productName;
        document.getElementById("platform").innerText = data.platform;
    })
    .catch(() => alert("Unable to fetch product details"));
}

// Save product
function trackProduct() {
    fetch(`${BASE_URL}/api/products/save`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(currentProduct)
    })
    .then(res => res.json())
    .then(data => {
        document.getElementById("result").innerText =
            "Product saved successfully (ID: " + data.id + ")";
    })
    .catch(() => alert("Error saving product"));
}

// ================= FLOW 2 =================

// Predict price using manual DB data
function getPrediction() {
    const productId = document.getElementById("productId").value;
    const list = document.getElementById("predictionList");

    list.innerHTML = "";

    fetch(`${BASE_URL}/api/predict/${productId}`)
        .then(res => res.json())
        .then(data => {
            data.forEach((price, index) => {
                const li = document.createElement("li");
                li.className = "bg-gray-100 p-2 rounded";
                li.innerText = `Day ${index + 1}: ₹${price.toFixed(2)}`;
                list.appendChild(li);
            });
        })
        .catch(() => alert("Prediction failed. Check product ID."));
}
