const DOMAIN = "localhost";
const PORT = "8080";

function getRequest(request) {
  const URL = "http://" + DOMAIN + ":" + PORT + request;
  fetch(URL, {
    method: "GET",
  })
    .then(async (response) => {
      //return response;

      const data = await response.json();
      return data;
      if (!response.ok) {
        const error = (data && data.message) || response.status;
        return Promise.reject(error);
      }
    })
    .catch((error) => {
      //setErrorMessage(error);
      console.error("There was an error!", error);
    });
}

function postRequest(request, data) {
  const URL = "http://" + DOMAIN + ":" + PORT + request;
  fetch(URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: data,
  })
    .then(async (response) => {
      return response;

      const data = await response.json();
      if (!response.ok) {
        const error = (data && data.message) || response.status;
        return Promise.reject(error);
      }
    })
    .catch((error) => {
      //setErrorMessage(error);
      console.error("There was an error!", error);
    });
}
