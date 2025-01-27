document.addEventListener('DOMContentLoaded', () => {
    let totalPrice = 0;

    document.querySelectorAll(".price").forEach(span => {
        totalPrice += parseFloat(span.textContent.trim());
    });


    document.getElementById("total-price").textContent = `총 가격: ${totalPrice} `;

    let img = document.createElement("img");
    img.src = "/img/gold.png";
    img.width = "12";
    img.alt = "gold";

    document.getElementById("total-price").appendChild(img);
});

