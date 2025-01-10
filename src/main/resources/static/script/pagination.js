function _pageItem(text, page, active = false) {
  const li = document.createElement("li");
  li.classList.add('page-item')

  if (active) li.classList.add('active');

  const anchor = document.createElement('a')
  anchor.innerText = text
  anchor.classList.add('page-link')
  anchor.href = location.pathname + usePage(page)

  li.append(anchor)

  return li;
}

/**
 * 동적으로 페이지네이션을 만드는 함수
 * a를 사용함
 * @author 원종호
 * @param {number} start
 * @param {number} end
 * @param {number} currentPage
 * @param prev
 * @param next
 */
function createPagination(start, end, currentPage,  prev = false, next = false) {
  const ul = document.createElement('ul')
  ul.classList.add('pagination')

  if (prev) {
    ul.append(_pageItem('«', start - 10))
  }

  for (let i = start; i <= end; i++) {
    ul.append(_pageItem(i, i, i === currentPage))
  }

  if (next) {
    ul.append(_pageItem('»', end + 1))
  }

  return ul;
}