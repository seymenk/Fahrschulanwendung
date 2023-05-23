function getTermine() {
  return fetch("/get-alle-termine")
    .then((response) => response.json())
    .catch((error) => {
      console.error("Fehler beim Abrufen der Termine:", error);
    });
}
