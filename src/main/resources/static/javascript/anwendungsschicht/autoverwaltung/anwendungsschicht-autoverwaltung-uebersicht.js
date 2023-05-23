async function fetchCars() {
    try {
        const cars = await getCars();
        displayCars(cars);
    } catch (error) {
        console.error('Fehler beim Abrufen der Autos:', error);
    }
}

async function getCars() {
    const response = await fetch('/getCars');
    return await response.json();
}
