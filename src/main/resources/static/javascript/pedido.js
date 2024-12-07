document.addEventListener('DOMContentLoaded', () => {
    const cartItemsContainer = document.getElementById('cart-items-container');
    const subtotalElement = document.getElementById('subtotal');
    const totalElement = document.getElementById('total');
    
    cartItemsContainer.innerHTML = `<p class="text-center">No hay productos en el carrito.</p>`;
    subtotalElement.textContent = 'S/. 0.00';
    totalElement.textContent = 'S/. 0.00';

    const cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
    let subtotal = 0;

    if (cartItems.length === 0) {
        cartItemsContainer.innerHTML = `
            <p class="text-center">No hay productos en el carrito.</p>
        `;
    } else {
        cartItems.forEach((product, index) => {
            const price = parseFloat(product.price.replace(/[^\d.-]/g, "")) || 0;
            const total = product.quantity * price;
            subtotal += total;

            const cartItemHTML = `
                <div class="cart-item d-flex align-items-center justify-content-between mb-3">
                    <img src="${product.image}" alt="${product.title}" />
                    <div class="ms-3">
                        <h5 class="titulo-pedido">${product.title}</h5>
                    </div>
                    <div class="d-flex align-items-center ms-auto actions">
                        <button class="btn btn-outline-danger" onclick="actualizarCantidad(${index}, -1)">-</button>
                        <input class="form-control text-center mx-2" style="width: 50px;" type="text" value="${product.quantity}" readonly />
                        <button class="btn btn-outline-danger" onclick="actualizarCantidad(${index}, 1)">+</button>
                        <p class="mb-0 ms-3" style="font-weight: bold;">S/. ${total.toFixed(2)}</p>
                        <button class="login-button btn-outline-danger ms-3" onclick="eliminarProducto(${index})">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#8C1A10">
                                <path d="M280-120q-33 0-56.5-23.5T200-200v-520h-40v-80h200v-40h240v40h200v80h-40v520q0 33-23.5 56.5T680-120H280Zm400-600H280v520h400v-520ZM360-280h80v-360h-80v360Zm160 0h80v-360h-80v360ZM280-720v520-520Z"/>
                            </svg>
                        </button>
                    </div>
                </div>
            `;
            cartItemsContainer.innerHTML += cartItemHTML;
        });
    }

    subtotalElement.textContent = `S/. ${subtotal.toFixed(2)}`;
    totalElement.textContent = `S/. ${subtotal.toFixed(2)}`;
});

// Actualizar cantidad del producto
function actualizarCantidad(index, change) {
    const cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
    cartItems[index].quantity = Math.max(1, cartItems[index].quantity + change);
    localStorage.setItem('cartItems', JSON.stringify(cartItems));
    location.reload();
}

// Eliminar producto del carrito
function eliminarProducto(index) {
    const cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
    cartItems.splice(index, 1);
    localStorage.setItem('cartItems', JSON.stringify(cartItems));
    location.reload();
}

function mostrarFormularioPago() {
    document.getElementById("cart-section").style.display = "none";
    document.getElementById("summary-section").style.display = "none";
    document.getElementById("payment-section").style.display = "block";
}

let summaryProducts = []; 
let total = 0;

document.getElementById("payment-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;

    const cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
    total = cartItems.reduce((sum, item) => sum + (item.quantity * parseFloat(item.price)), 0);

    document.getElementById("summary-name").textContent = name;
    document.getElementById("summary-email").textContent = email;
    document.getElementById("summary-address").textContent = address;

    // Almacenar productos en summaryProducts
    summaryProducts = [];
    cartItems.forEach(item => {
        summaryProducts.push(`${item.title} x${item.quantity} - S/.${(item.quantity * parseFloat(item.price)).toFixed(2)}`);
    });

    document.getElementById("payment-section").style.display = "none";
    document.getElementById("purchase-summary").style.display = "block";
});

function preparePDFContent() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;
    const cartItems = JSON.parse(localStorage.getItem("cartItems")) || [];
    const pdfProductsContainer = document.getElementById("pdf-products");

    // Llena los datos del cliente
    document.getElementById("pdf-client-name").textContent = name;
    document.getElementById("pdf-client-email").textContent = email;
    document.getElementById("pdf-client-address").textContent = address;

    // Llena la tabla de productos
    pdfProductsContainer.innerHTML = "";
    let total = 0;

    cartItems.forEach(item => {
        const price = parseFloat(item.price.replace(/[^\d.-]/g, "")) || 0;
        const itemTotal = item.quantity * price;
        total += itemTotal;

        const rowHTML = `
            <tr>
                <td style="border: 1px solid #ccc; padding: 8px;">${item.title}</td>
                <td style="border: 1px solid #ccc; padding: 8px; text-align: center;">${item.quantity}</td>
                <td style="border: 1px solid #ccc; padding: 8px; text-align: right;">S/. ${price.toFixed(2)}</td>
                <td style="border: 1px solid #ccc; padding: 8px; text-align: right;">S/. ${itemTotal.toFixed(2)}</td>
            </tr>
        `;
        pdfProductsContainer.innerHTML += rowHTML;
    });

    // Llena el total
    document.getElementById("pdf-total").textContent = total.toFixed(2);
}

async function generatePDF() {
    // Prepara el contenido dinámico
    preparePDFContent();

    // Selecciona el contenedor del PDF
    const pdfContent = document.getElementById("pdf-content");

    // Muestra el contenedor temporalmente
    pdfContent.style.display = "block";

    // Genera la imagen con html2canvas
    const canvas = await html2canvas(pdfContent, { scale: 2 });
    const imgData = canvas.toDataURL("image/png");

    // Oculta el contenedor nuevamente
    pdfContent.style.display = "none";

    // Crea el PDF
    const pdf = new jspdf.jsPDF("p", "mm", "a4");
    const pdfWidth = pdf.internal.pageSize.getWidth();
    const pdfHeight = (canvas.height * pdfWidth) / canvas.width;

    pdf.addImage(imgData, "PNG", 0, 0, pdfWidth, pdfHeight);
    pdf.save("resumen_compra.pdf");

    // Vaciar el carrito después de generar el PDF
    localStorage.removeItem('cartItems');
    
}