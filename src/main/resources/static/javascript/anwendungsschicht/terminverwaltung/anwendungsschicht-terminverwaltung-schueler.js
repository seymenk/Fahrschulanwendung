function isWeekend(dateString) {
  const inputDate = new Date(dateString);
  const day = inputDate.getDay();
  return day === 0 || day === 6;
}
