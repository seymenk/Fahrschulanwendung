function getMeineTermine() {
  return fetch("/get-meine-termine")
    .then((response) => response.json())
    .catch((error) => {
      console.error("Fehler beim Abrufen der Termine:", error);
    });
}
