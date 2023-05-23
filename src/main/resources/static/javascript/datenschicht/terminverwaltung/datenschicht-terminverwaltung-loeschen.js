async function deleteTerminById(id) {
  try {
    const response = await fetch(`/termin/${id}`, {
      method: "DELETE",
    });

    if (!response.ok) {
      const error = new Error(response.statusText);
      error.status = response.status;
      throw error;
    }

    return true;
  } catch (error) {
    console.error("Fehler beim Löschen des Termins:", error);
    throw error;
  }
}
