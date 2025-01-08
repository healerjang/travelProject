async function getReplyList(freeBoardNo, page, size) {
    const response = await axios.get(`/replies/replyList/${freeBoardNo}/${page}/${size}`)
    return response.data;
}