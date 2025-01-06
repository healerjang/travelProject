
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
  return await axios.post("/api/location", { country, city })
}

/** 엘리먼트를 만든다. */
function _el (tagName, text = undefined) {
  const el = document.createElement(tagName);
  if (text != undefined) el.appendChild(document.createTextNode(text));
  return el;
}