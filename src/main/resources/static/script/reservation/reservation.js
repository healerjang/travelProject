async function getUserReservation({reservationOrder, page}){
    const result = await axios.get(`/reservation/userReservation/${reservationOrder}`, {params: {page}})
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

async function deleteReservationNow(reservationNo){
    const result = await axios.delete(`/reservation/delete/now/${reservationNo}`)
    return result.data
}

async function feeReservation(reservationNo){
    const result = await axios.put(`/reservation/fee/${reservationNo}`)
    return result.data
}