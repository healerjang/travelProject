const rankImageBoxes = [];
const rankImageContainer = document.querySelector('.rankImageContainer');
const rankImageContainerLeftArrow = document.querySelector('.rankImageContainerLeftArrow');
const rankImageContainerRightArrow = document.querySelector('.rankImageContainerRightArrow');
const rankImageContainerWidth = rankImageContainer.offsetWidth;
let canMoveToRightRankImageBoxes = 0;
let canMoveToLeftRankImageBoxes = 0;

function getRankImage(num) {
    for (let i = 0; i < num; i++) {
        const rankImageBox = document.createElement('div');
        rankImageBox.classList.add("rankImageBox");
        rankImageBoxes.push(rankImageBox);
        addRankImage(rankImageBox);
        rankImageContainer.appendChild(rankImageBox);
    }
}

function addRankImage(rankImageBox) {
    const left = (rankImageBoxes.length - 1) * rankImageContainerWidth
    rankImageBox.style.left = `${left}px`;
}

function moveToRightRankImageBoxes() {
    if (canMoveToRightRankImageBoxes < 9) {
        for (const rankImageBox of rankImageBoxes) {
            const leftPosition = parseFloat(rankImageBox.style.left) - rankImageContainerWidth;
            rankImageBox.style.left = `${leftPosition}px`;
        }
        canMoveToRightRankImageBoxes ++;
        canMoveToLeftRankImageBoxes ++;
    }
}

function moveToLeftRankImageBoxes() {
    if (canMoveToLeftRankImageBoxes > 0) {
        for (const rankImageBox of rankImageBoxes) {
            const leftPosition = parseFloat(rankImageBox.style.left) + rankImageContainerWidth;
            rankImageBox.style.left = `${leftPosition}px`;
        }
        canMoveToRightRankImageBoxes --;
        canMoveToLeftRankImageBoxes --;
    }
}

rankImageContainerRightArrow.addEventListener('click', e=> {
    moveToRightRankImageBoxes()
})

rankImageContainerLeftArrow.addEventListener('click', e=> {
    moveToLeftRankImageBoxes()
})

getRankImage(10);