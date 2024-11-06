const linksNav = document.querySelectorAll('.nav-link');
const currentPath = window.location.pathname;
linksNav.forEach(link => {
    const linkPath = new URL(link.href).pathname;

    if (currentPath === linkPath) {
        link.classList.add('active'); 
        link.style.fontWeight = '650'; 
    } else {
        link.classList.remove('active'); 
        link.style.fontWeight = 'normal'; 
    }
});