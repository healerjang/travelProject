async function getCart({page}) {
    const result = await axios.get(`/cart/read`, {params: {page}})
    return result.data
}

async function makeReservation() {
    const result = await axios.post(`/cart/makeReservation`)
    return result.data
}

async function delCart(productNo) {
    const result = await axios.get(`/cart/del/${productNo}`)
    return result.data
}

async function addCart(productNo) {
    const result = await axios.get(`/cart/add/${productNo}`)
    return result.data
}

async function getImage(imgPath) {
    const result = await axios.get(`/productImage/${imgPath}`)
    return result.data
}

async function makeReservationImmediately(productNo) {
    const reservationDTO = {
        productNo: `${productNo}`
    }
    const result = await axios.post(`/reservation/reg`, reservationDTO)
    return result.data
}