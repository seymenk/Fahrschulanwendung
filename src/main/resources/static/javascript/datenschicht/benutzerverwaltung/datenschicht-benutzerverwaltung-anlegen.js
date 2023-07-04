async function createUser(user, role) {
	console.log("User object:", user);
	let url;
	if (role === "Fahrschüler") {
		url = "/create-fahrschueler";
	} else if (role === "Fahrlehrer") {
		url = "/create-fahrlehrer";
	}

	const response = await fetch(url, {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(user),
	});
	return response.ok;
}