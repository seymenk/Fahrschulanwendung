function getAllFahrlehrer() {
  return fetch("/get-alleFahrlehrer").then((response) => response.json());
}

function getIDandDate(data) {
  return fetch("/getIDandDate", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  }).then((response) => response.json());
}

function bookTerminById(data) {
  return fetch("/bookTermin", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  }).then((response) => response.json());
}
