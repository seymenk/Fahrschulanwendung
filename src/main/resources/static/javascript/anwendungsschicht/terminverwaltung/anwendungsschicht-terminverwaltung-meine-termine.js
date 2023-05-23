function processMeineTermine(termine) {
  return termine.map((termin) => ({
    ...termin,
    backgroundColor: termin.gebucht ? "green" : "red",
  }));
}
