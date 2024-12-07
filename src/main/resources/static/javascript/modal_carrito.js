const btnCart = document.querySelector('.container-cart-icon');
const containerCartProducts = document.querySelector('.countainer-cart-products');// Corregido nombre de clase
const rowProduct = document.querySelector('.row-product');
const valortotal = document.querySelector('.total-pagar');
const countProducts = document.querySelector('#contador-productos');
const btnProcesarPedido = document.querySelector('#procesar-pedido');

let allProducts = [];

const guardarCartToLocalStorage = () => {
    localStorage.setItem('cartItems', JSON.stringify(allProducts));
};

const cargarCartFromLocalStorage = () => {
    const savedCart = localStorage.getItem('cartItems');
    allProducts = savedCart ? JSON.parse(savedCart) : [];
};

btnCart.addEventListener('click', () => {
    console.log('Carrito clickeado');
    if (containerCartProducts.classList.contains('hidden-cart')) {
        containerCartProducts.classList.remove('hidden-cart');
    } else {
        containerCartProducts.classList.add('hidden-cart');
    }
});

const showHTML = () => {
    rowProduct.innerHTML = '';

    if (!allProducts.length) {
        rowProduct.innerHTML = `
            <p class="cart-empty">Su carrito de compras está vacío</p>
        `;
        valortotal.innerText = 'S/.0.00';
        countProducts.innerText = 0;
        return;
    }
    console.log(btnCart, containerCartProducts);

    let total = 0;
    let totalOfProducts = 0;

    allProducts.forEach((product, index) => {
        const containerProduct = document.createElement('div');
        containerProduct.classList.add('cart-product');
        containerProduct.innerHTML = `
            <div class="info-cart-product">
                <span class="cantidad-producto-carrito">${product.quantity}</span>
                <p class="titulo-producto-carrito">${product.title}</p>
                <span class="precio-producto-carrito">${product.price}</span>
            </div>
            <i class="bi bi-x-circle boton-cerrar" data-index="${index}"></i>
        `;
        rowProduct.append(containerProduct);

        const priceNumber = parseFloat(product.price.replace(/[^\d.]/g, "")) || 0;
        total += product.quantity * priceNumber;
        totalOfProducts += product.quantity;
    });

    valortotal.innerText = `S/.${total.toFixed(2)}`;
    countProducts.innerText = totalOfProducts;

    guardarCartToLocalStorage();
};

document.addEventListener('click', (e) => {
    if (e.target.closest('.boton-compra')) {
        const productCard = e.target.closest('.card');
        const productTitle = productCard.querySelector('.titulo-secundario-productos').textContent;
        const productPrice = productCard.querySelector('.numero-card').textContent;
        const existingProductIndex = allProducts.findIndex(p => p.title === productTitle);

        if (existingProductIndex !== -1) {
            allProducts[existingProductIndex].quantity++;
        } else {
            const newProduct = {
                title: productTitle,
                price: productPrice,
                quantity: 1
            };
            allProducts.push(newProduct);
        }

        showHTML();
    }
});

rowProduct.addEventListener('click', (e) => {
    if (e.target.classList.contains('boton-cerrar')) {
        const index = e.target.dataset.index;
        allProducts.splice(index, 1);
        guardarCartToLocalStorage();
        showHTML(); 
    }
});

btnProcesarPedido.addEventListener('click', () => {
    window.location.href = '/pedido';
});

document.addEventListener('DOMContentLoaded', () => {
    cargarCartFromLocalStorage();
    showHTML();
});

document.addEventListener('click', (e) => {
    if (e.target.classList.contains('boton-cerrar')) return;

    if (!containerCartProducts.contains(e.target) && !btnCart.contains(e.target)) {
        containerCartProducts.classList.add('hidden-cart');
    }
});