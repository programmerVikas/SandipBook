// for showing the name of file in popup modal while posting
const actualBtn = document.getElementById('actual-btn');

const fileChosen = document.getElementById('file-chosen');

actualBtn.addEventListener('change', function(){
  fileChosen.textContent = this.files[0].name
 
})

// for showing file in popup modal while posting 

function img_pathUrl(input){
  if (input.files[0].type.match('image.*')) {
    $('#pdf_url')[0].src = "";
    $('#img_url')[0].src = (window.URL ? URL : webkitURL).createObjectURL(input.files[0]);
    $('#pdf_url')[0].style.display = 'none';
  } else {
    $('#pdf_url')[0].style.display = 'block';
     $('#img_url')[0].src = "";
    $('#pdf_url')[0].src = (window.URL ? URL : webkitURL).createObjectURL(input.files[0]).concat("#toolbar=0");
  }
    
}
// post text area auto height while writing
$(".postTextArea").each(function () {
  this.setAttribute("style", "height:120px;overflow-y:hidden;  font-size : 15px;");
}).on("input", function () {
  this.style.height = "120px";
  this.style.height = (this.scrollHeight) + "px";
});

// comment text area auto height while writing
$(".commentTextArea").each(function () {
  this.setAttribute("style", "height:70px;overflow-y:hidden;margin-left: -40px; font-size : 13px; ");
}).on("input", function () {
  this.style.height = "70px";
  this.style.height = (this.scrollHeight) + "px";
});