function checkDeadline() {
    let date = document.getElementById("deadline").value;

    const currentDate = new Date();
    const inputDate = date.split("-");

    let year = inputDate[0];
    let month = inputDate[1];
    let day = inputDate[2];

    if(year >= currentDate.getFullYear() && month >= currentDate.getMonth() && day > currentDate.getDate()){
        return true;
    } else{
            alert("Deadline should be at list tomorrow");
            return false;
    }

}