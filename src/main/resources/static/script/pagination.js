/**
 * 동적으로 페이지네이션을 만드는 함수
 * a를 사용함
 * @author 원종호
 * @param {number} start
 * @param {number} end
 * @param {number} currentPage
 * @param {string} link
 * @param size
 */
function createPagination(start, end, currentPage,  link, size) {
  const ul = document.createElement('ul')
  ul.classList.add('pagination')

  for (let i = start; i <= end; i++) {
    const li = document.createElement('li')
    li.classList.add('page-item')

    if (i === currentPage) li.classList.add('active')

    const anchor = document.createElement('a')
    anchor.textContent = i
    anchor.classList.add('page-link')
    anchor.href = link + `?page=${i}&size=${size}`
    li.append(anchor)
    ul.append(li)
  }

  return ul;
}