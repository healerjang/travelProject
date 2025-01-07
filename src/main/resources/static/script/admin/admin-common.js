/**
 * 일 단위 숫자를 나눠서 대략적인 큰 단위로 나타낸다.
 *
 * ex)
 * - 60일 -> 2개월
 * - 380일 -> 1년
 * - 900일 -> 2년
 *  */
function periodExpression(days) {
  const sign = Math.sign(days);
  days = Math.abs(days);
  const chunks = []

  if (days > 0 && days < 30) {
    chunks.push(`${days}일`)
  } else {
    const years = Math.floor(days / 365);
    const rY = days % 365;

    const months = Math.floor(rY / 30);
    if (years > 0) chunks.push(`${years}년`)
    if (months > 0) chunks.push(`${months}개월`)
  }

  if (sign > 0) chunks.push("남음")
  else if (sign < 0) chunks.push("지남")
  else chunks.push("오늘")

  return chunks.join(" ")
}

/** option 엘리먼트를 만든다 */
function optEl(text, value = undefined) {
  const el = document.createElement("option");
  el.textContent = text;
  if (value) el.value = value;
  return el
}

/** select 엘리먼트의 내용물을 초기화한다. */
function clearSelect(el) {
  const top = el.querySelector('option[selected]').innerText;
  el.innerHTML = `<option selected>${top}</option><option disabled>-------------</option>`
}

/** 여행지를 추가한다. */
async function registerLocation(country, city) {
  return await axios.post('/api/admin/location', { country, city })
}

/**
 * 엘리먼트를 만든다.
 * @param {string} tagName
 * @param {(number | string | Node | ((l: HTMLElement) => any))} args
 * */
function _el (tagName, ...args) {
  const el = document.createElement(tagName);
  for (const arg of args) {
    if ((typeof arg === 'number') || (typeof arg === 'string') || arg instanceof Node) el.append(arg)
    else if (arg instanceof Function) arg(el)
  }
  return el;
}