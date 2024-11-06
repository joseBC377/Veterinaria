const btnCart = document.querySelector('.container-cart-icon');
const containerCartProducts = document.querySelector('.countainer-cart-products');
const rowProduct = document.querySelector('.row-product');
const productList = document.querySelector('.container-items');
const valortotal = document.querySelector('.total-pagar');
const countProducts = document.querySelector('#contador-productos');
let allProducts = [];

btnCart.addEventListener('click', () => {
    containerCartProducts.classList.toggle('hidden-cart');
});

productList.addEventListener('click', (e) => {
    const button = e.target.closest('boton-compra');
    if (button) {
        const product = button.parentElement;
        const infoProduct = {
            quantity: 1,
            title: product.querySelector('h4').textContent, 
            price: product.querySelector('p').textContent, 
        };

        const exists = allProducts.some(product => product.title === infoProduct.title);
        if (exists) {
            const products = allProducts.map(product => {
                if (product.title === infoProduct.title) {
                    product.quantity++;
                    return product;
                }
                return product;
            });
            allProducts = [...products];
        } else {
            allProducts = [...allProducts, infoProduct];
        }

        showHTML();
    }
});

rowProduct.addEventListener('click', (e) => {
    if (e.target.classList.contains('boton-cerrar')) {
        const product = e.target.parentElement;
        const title = product.querySelector('p').textContent;
        allProducts = allProducts.filter(product => product.title !== title);
        showHTML();
    }
});

const showHTML = () => {
    rowProduct.innerHTML = ''; 

    if (!allProducts.length) {
        rowProduct.innerHTML = `
            <p class="cart-empty">Su carrito de compras está vacío</p>
        `;
        document.querySelector('.cart-total');
        return;
    }

    document.querySelector('.cart-total');
    let total = 0;
    let totalOfProducts = 0;

    allProducts.forEach(product => {
        const containerProduct = document.createElement('div');
        containerProduct.classList.add('cart-product');
        containerProduct.innerHTML = `
            <div class="info-cart-product">
                <span class="cantidad-producto-carrito">${product.quantity}</span>
                <p class="titulo-producto-carrito">${product.title}</p>
                <span class="precio-producto-carrito">${product.price}</span>
            </div>
            <i class="bi bi-x-circle boton-cerrar"></i>
        `;
        rowProduct.append(containerProduct);

        const priceNumber = parseFloat(product.price.replace(/[^\d.-]/g, ""));
        total += product.quantity * priceNumber;
        totalOfProducts += product.quantity;
    });

    valortotal.innerText = 'S/${total.toFixed(2)}';
    countProducts.innerText = totalOfProducts;
};

document.addEventListener('DOMContentLoaded', () => {
    showHTML();
});