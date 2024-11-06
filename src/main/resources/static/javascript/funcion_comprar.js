function verContenido(animal, subcategoria) {
    const contentSection = document.getElementById(`contenido-${animal}`);
    const productosSeleccionados = {
        perros: {
            accesorios: accesorios_perros,
            alimentos: alimentos_perros,
            salud: salud_perros,
            ropa: ropa_perros
        },
        gatos: {
            accesorios: accesorios_gatos,
            alimentos: alimentos_gatos,
            salud: salud_gatos,
            ropa: ropa_gatos
        }
    }[animal][subcategoria];

    if (productosSeleccionados) {
        const titulo = `${subcategoria.charAt(0).toUpperCase() + subcategoria.slice(1)} para ${animal.charAt(0).toUpperCase() + animal.slice(1)}`;
        const contenido = `
        <h2 class="titulo-primario-productos">${titulo}</h2>
        <p>Aquí puedes encontrar productos de ${subcategoria}</p>
        <div class="row text-center">
          ${productosSeleccionados.map(producto => `
            <div class="col-12 col-md-6 col-lg-3 mb-4">
              <div class="card">
                <img src="${producto.imagen}" class="card-img-top imagen-servicio" alt="${producto.titulo}">
                <div class="card-body">
                  <h4 class="titulo-secundario-productos">${producto.titulo}</h4>
                  <p class="numero-card">${producto.precio}</p>
                  <button class="boton-compra">
                    <i class="bi bi-cart-dash"> Comprar</i>
                  </button>
                </div>
              </div>
            </div>
          `).join('')}
        </div>
      `;
        contentSection.innerHTML = contenido;
    }
}
