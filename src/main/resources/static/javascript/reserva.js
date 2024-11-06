const step1 = document.getElementById('step1');
const step2 = document.getElementById('step2');
const step3 = document.getElementById('step3');
const step4 = document.getElementById('step4'); 
const seguir1 = document.getElementById('seguir1');
const retroceder2 = document.getElementById('retroceder2');
const seguir2 = document.getElementById('seguir2');
const veterinarianList = document.getElementById('veterinarian-list');
const veterinariansByService = {
    'Vacunación': { 
        price: 50, 
        vets: [
            { name: 'Héctor Lozano Arias', image: '/Multimedia/reserva/Imagen_Hector.png' },
            { name: 'Gustavo Álvarez', image: '/Multimedia/reserva/Imagen_Gustavo.png' },
            { name: 'Sara Campuzano Herrera', image: '/Multimedia/reserva/Imagen_Sara.png' },
            { name: 'Mari Carmen Rodríguez Díaz', image: '/Multimedia/reserva/Imagen_Mari.png' },
            { name: 'Manuel Herrera', image: '/Multimedia/reserva/Imagen_Manuel.png' }
        ]
    },
    'Desparasitación': { 
        price: 60, 
        vets: [
            { name: 'Lily Velandia', image: '/Multimedia/About/lily_oncología.png' },
            { name: 'Fernando Espinoza', image: '/Multimedia/About/fernando_general.png' }
        ]
    },
    'Diagnóstico': { 
        price: 40, 
        vets: [
            { name: 'Sorely Trujillo', image: '/Multimedia/About/sorely_general.png' },
            { name: 'Carlos Fernández', image: '/Multimedia/reserva/Imagen_Carlos.png' }
        ]
    },
    'Consultoría Veterinaria': { 
        price: 100, 
        vets: [
            { name: 'Ana González', image: '/Multimedia/reserva/Imagen_Ana.png' },
            { name: 'Pablo Ruiz', image: '/Multimedia/reserva/Imagen_Pablo.png' }
        ]
    },
    'Comportamiento animal': { 
        price: 70, 
        vets: [
            { name: 'Elena Martínez', image: '/Multimedia/reserva/Imagen_Elena.png' },
            { name: 'Miguel Sánchez', image: '/Multimedia/reserva/Imagen_Miguel.png' }
        ]
    },
    'Grooming': { 
        price: 60, 
        vets: [
            { name: 'Camila Ramirez', image: '/Multimedia/reserva/Imagen_Camila.png' },
            { name: 'Santiago Burgos', image: '/Multimedia/reserva/Imagen_Santiago.png' }
        ]
    }
};

seguir1.addEventListener('click', function () {
    const selectedService = document.querySelector('input[name="service"]:checked');

    if (selectedService) {
        step1.style.display = 'none';
        step2.style.display = 'block';

        veterinarianList.innerHTML = ''; 
        const veterinarians = veterinariansByService[selectedService.value].vets;

        veterinarians.forEach(vet => {
            const vetItem = document.createElement('li');
            vetItem.classList.add('appointment-item');

            const vetInput = document.createElement('input');
            vetInput.type = 'radio';
            vetInput.name = 'veterinarian';
            vetInput.value = vet.name;
            vetInput.id = vet.name;

            const vetLabel = document.createElement('label');
            vetLabel.htmlFor = vet.name;
            vetLabel.textContent = vet.name;

            const vetImg = document.createElement('img');
            vetImg.src = vet.image;
            vetImg.alt = vet.name;

            vetItem.appendChild(vetInput);
            vetItem.appendChild(vetImg);
            vetItem.appendChild(vetLabel);
            veterinarianList.appendChild(vetItem);
        });
    } else {
        alert("Por favor, selecciona un servicio.");
    }
});
retroceder2.addEventListener('click', function () {
    step2.style.display = 'none';
    step1.style.display = 'block';
});

//formulario2
seguir2.addEventListener('click', function () {
    const selectedVeterinarian = document.querySelector('input[name="veterinarian"]:checked');
    if (selectedVeterinarian) {
        step2.style.display = 'none';
        step3.style.display = 'block'; 
    } else {
        alert("Por favor, selecciona un veterinario.");
    }
});
//formulario 3
const availableTimesByDate = {
    '2024-09-25': ['09:00', '10:00', '11:00', '12:00', '13:00'],
    '2024-09-26': ['09:00', '11:00', '12:00', '14:00', '15:00'],
    '2024-09-28': ['09:00', '10:00', '12:00', '13:00'],
    '2024-10-02': ['09:00', '10:00', '11:00', '12:00', '13:00'],
    '2024-10-05': ['09:00', '11:00', '12:00', '14:00', '15:00'],
    '2024-10-09': ['09:00', '10:00', '12:00', '13:00'],
    '2024-10-10': ['09:00', '10:00', '11:00', '12:00', '13:00'],
    '2024-10-11': ['09:00', '11:00', '12:00', '14:00', '15:00'],
    '2024-10-15': ['09:00', '10:00', '12:00', '13:00']
};

const occupiedDates = ['2024-09-27', '2024-09-29', '2024-09-30', '2024-10-12', '2024-10-03', '2024-10-01', '2024-10-20', '2024-10-23'];



const appointmentDateInput = document.getElementById('appointment-date');
const availableTimesList = document.getElementById('available-times');

flatpickr("#appointment-date", {
    disable: occupiedDates,
    onChange: function (selectedDates) {
        const date = selectedDates[0].toISOString().split('T')[0];
        const availableTimes = availableTimesByDate[date] || [];

        const availableTimesList = document.getElementById('available-times');
        availableTimesList.innerHTML = '';

        if (availableTimes.length > 0) {
            availableTimes.forEach(time => {
                const listItem = document.createElement('li');

                const timeButton = document.createElement('button');
                timeButton.textContent = time;
                timeButton.style.backgroundColor = 'transparent';
                timeButton.style.border = 'none';
                timeButton.style.padding = '5px';
                timeButton.style.margin = '5px';
                timeButton.style.cursor = 'pointer';
                timeButton.style.color = '#000'; 
                timeButton.style.transition = 'color 0.3s'; 

                timeButton.onclick = function () {
                    const buttons = document.querySelectorAll('button');
                    buttons.forEach(btn => {
                        btn.style.color = '#000'; 
                    });
                    timeButton.style.color = '#87CEEB'; 
                    timeButton.setAttribute('name', 'appointment-time');
                    timeButton.value = time; 
                };

                listItem.appendChild(timeButton);
                availableTimesList.appendChild(listItem);
            });
        } else {
            availableTimesList.innerHTML = '<li>No hay horarios disponibles</li>';
        }
    },
    onDayCreate: function (dObj, dStr, fp, dayElem) {
        const date = dayElem.dateObj.toISOString().split('T')[0];
        if (availableTimesByDate[date]) {
            dayElem.classList.add('free');
        } else if (occupiedDates.includes(date)) {
            dayElem.classList.add('occupied');
        }
    }
});
document.getElementById('retroceder3').addEventListener('click', function () {
    step3.style.display = 'none';
    step2.style.display = 'block';
});
document.getElementById('seguir3').addEventListener('click', function () {
    const selectedVeterinarian = document.querySelector('input[name="veterinarian"]:checked');
    const appointmentDate = appointmentDateInput.value;
    const selectedTime = document.querySelector('button[name="appointment-time"]');
    const selectedService = document.querySelector('input[name="service"]:checked').value;
    const servicePrice = veterinariansByService[selectedService].price;

    if (selectedVeterinarian && appointmentDate && selectedTime) {
        step3.style.display = 'none';
        step4.style.display = 'block';
        const summary = `
            Servicio: ${selectedService}<br>
            Veterinario: ${selectedVeterinarian.value}<br>
            Fecha: ${appointmentDate}<br>
            Hora: ${selectedTime.value}<br>
            Precio: S/. ${servicePrice}
        `;
        document.getElementById('appointment-summary').innerHTML = summary;
    } else {
        alert("Por favor, selecciona un veterinario, una fecha y una hora.");
    }
});

document.getElementById('otra-cita').addEventListener('click', function () {
    step4.style.display = 'none'; 
    step1.style.display = 'block'; 

    document.querySelector('input[name="service"]:checked').checked = false; 
    document.querySelectorAll('input[name="veterinarian"]:checked').forEach(input => input.checked = false); 
    document.getElementById('appointment-date').value = ''; 
    document.getElementById('available-times').innerHTML = ''; 
    document.getElementById('appointment-summary').innerHTML = '';
});
