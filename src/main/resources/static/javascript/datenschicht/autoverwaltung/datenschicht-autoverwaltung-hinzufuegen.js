async function createAuto(auto) {
	const response = await fetch("/create-auto", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(auto),
	});

	return response;
}