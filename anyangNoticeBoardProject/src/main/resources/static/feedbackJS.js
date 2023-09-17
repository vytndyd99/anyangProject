const add_textbox = (obj) => {
    const box = obj.parentElement.parentElement;
    const newP = document.createElement("tr");

    newP.innerHTML = "<tr class='form-group'><td>메일 주소</td><td><input type='text' class='form-control' name='address' ></td><td><input type='button' class='form-control' value='삭제' onclick='opt_remove(this)'></td></tr>";
    box.parentNode.insertBefore(newP, box.nextSibling);
}

const add_textbox2 = (obj) => {
    const box = obj.parentElement.parentElement;
    const newP = document.createElement("tr");

    newP.innerHTML = "<tr class='form-group'><td>참조 메일 주소</td><td><input type='text' class='form-control' name='ccAddress' ></td><td><input type='button' class='form-control' value='삭제' onclick='opt_remove2(this)'></td></tr>";
    box.parentNode.insertBefore(newP, box.nextSibling);
}

const opt_remove = (obj) => {
    obj.parentElement.parentElement.parentElement.removeChild(obj.parentElement.parentElement);
}
const opt_remove2 = (obj) => {
    obj.parentElement.parentElement.parentElement.removeChild(obj.parentElement.parentElement);
}

const alertMessage = () => {
    alert("메일이 보내졌습니다!");
}