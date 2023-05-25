async function getCars() {
    const response = await fetch('/getCars');
    return await response.json();
}
