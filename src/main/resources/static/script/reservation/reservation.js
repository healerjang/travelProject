async function getUserReservation({reservationOrder, page, size}){
    const result = await axios.get(`/reservation/userReservation/${reservationOrder}`, {params: {page, size}})
    return result.data
}

async function getAdminReservation(productNo){
    const result = await axios.get(`/reservation/adminReservation/${productNo}`)
    return result.data
}

async function deleteReservation(reservationNo){
    const result = await axios.put(`/reservation/delete/${reservationNo}`)
    return result.data
}
async function refundReservation(reservationNo,refundPercent){
    const obj = {
        reservationNo: `${reservationNo}`,
        refundPercent: `${refundPercent}`
    }
    const result = await axios.post(`/reservation/refund`, obj)
    return result.data
}

async function deleteReservationNow(reservationNo){
    const result = await axios.delete(`/reservation/delete/now/${reservationNo}`)
    return result.data
}

async function feeReservation(reservationNo){
    const result = await axios.put(`/reservation/fee/${reservationNo}`)
    return result.data
}

async function getMemberPoint() {
    const result = await axios.get(`/reservation/memberPoint`)
    return result.data
}

async function getImage(imgPath) {
    const result = await axios.get(`/productImage/${imgPath}`)
    return result
}