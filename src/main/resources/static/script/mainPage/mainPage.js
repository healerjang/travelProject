async function getContent(page, size, locationNo, startDate, endDate) {
    const params  = {
        page: page,
        size: size,
        locationNo: locationNo,
        startDate: startDate,
        endDate: endDate
    }

    const response = await axios.get(`/mainPage/content`, {params});

    if (page * size >= response.data.total) searchTotal = true;
    return response.data
}

async function getCountries() {
    const response = await axios.get('/location/country');
    return response.data;
}

async function getCities(country) {
    const response = await axios.get(`/location/city/${country}`);
    return response.data;
}