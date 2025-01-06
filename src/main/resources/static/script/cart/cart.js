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
    return result
}

async function addCart(productNo){
    console.log(productNo)
    const result = await axios.get(`/cart/add/${productNo}`)
    return result
}