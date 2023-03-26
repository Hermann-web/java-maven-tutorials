<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
  <title>My App</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
  <script>
      var RESULT = null;
      var INPUTS = null;
  </script>  
  
  <script>
    // Wait for the DOM to be ready
    document.addEventListener("DOMContentLoaded", function() {
      // Find the form element



      const form = document.querySelector("#contact");

      // Listen for the form's "submit" event
      form.addEventListener("submit", function(event) {
        // Prevent the default form submission behavior
        event.preventDefault();

        // Collect the input values and put them in an array
        const inputs = Array.from(document.querySelectorAll("#contact input[type=text]"));
        const texts = inputs.map(input => input.value.trim()).filter(input=>!!input);
        console.log(texts)
        $.ajax({
          type: "POST",
          url: "simple",
          data: { texts: JSON.stringify(texts) },
          success: function(response) {
            console.log("Server response:", response);
            document.querySelector("#result").textContent = response.result;
            document.querySelector("#nb_texts").textContent = response.nb_texts;
            document.querySelector("#laps").textContent = response.laps;
            document.querySelector("#match").textContent = response.match;
            INPUTS = response.texts;
            RESULT = response.result;
          },
          error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX error:", textStatus, errorThrown);
          }
        });
      });
    });
  </script>  


  <script>
    // Wait for the DOM to be ready
    document.addEventListener("DOMContentLoaded", function() {
      
      // Find the form element
      const saveBtn = document.querySelector("#save-btn");

      // Listen for the form's "submit" event
      saveBtn.addEventListener('click', function(event) {
        if(INPUTS===null){
        alert("submit the inputs first !");
        return;
      }
        // Prevent the default form submission behavior
        event.preventDefault();

        $.ajax({
          type: "POST",
          url: "simple",
          data: { save: 1, result: RESULT,texts: JSON.stringify(INPUTS) },
          success: function(response) {
            console.log("Server response:", response);
            alert('result saved !');
          },
          error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX error:", textStatus, errorThrown);
          }
        });
      });
    });
  </script>
</head>


<style>
  body {
    background-color: #F5F5F5;
    font-family: Arial, sans-serif;
  }
  h1 {
    color: #333;
    font-size: 48px;
    font-weight: bold;
    text-align: center;
    margin-top: 50px;
    margin-bottom: 30px;
  }
  .main-div {
    background-color: #fff;
    padding: 30px;
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
  }
  h3 {
    color: #333;
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 20px;
  }
  input[type=text],.div-res {
    padding: 10px;
    border-radius: 5px;
    border: none;
    background-color: #F5F5F5;
    box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
    word-wrap: break-all;
  }
  input[type=submit] {
    padding: 10px 20px;
    background-color: #333;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  input[type=submit]:hover {
    background-color: #555;
  }
  #my-inputs {
    margin-bottom: 20px;
  }
  #add-btn, #save-btn {
    padding: 10px 20px;
    margin-right: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  #add-btn:hover, #save-btn:hover {
    background-color: #555;
    color: #fff;
  }
  #add-btn {
    background-color: #333;
    color: #fff;
  }
  #save-btn {
    background-color: #28A745;
    color: #fff;
  }
  #result {
    font-size: 21px;
    font-weight: bold;
    color: #28A745;
    text-align: center;
  }
  .div-res{
    margin: 5px;
    padding: 5px;
    font-size: 33px;
  }
  .main-div  span{
    color: blue;
  }
  .my-div-btn {
    display: flex;
    align-content: space-around;
  }
  .my-div-btn>button{
    margin-left: 15px;
    margin-right: 15px;
  }
  .div-res div {
    width: max-content;
    margin: auto;
  }
</style>

<body>
  <div class="container" style="width: 50%;">
    <h1>Text Reconstructor App</h1>
    <form id="contact" class="main-div" method="post">
      <div><h3>Enter some texts</h3></div>
      <div class="row" id="my-inputs">
        <!-- Iterate over the list and put each string into a span element -->
        <c:forEach items="${requestScope.texts}" var="text" varStatus="status">
          <div><label for="text${status.index}">Text ${status.index + 1}</label><input type="text" class="form-control" name="text${status.index}" id="text${status.index}" placeholder="text..." value="${text}"></div>
        </c:forEach>
      </div>
      <div class="my-div-btn">
        <button id="add-btn" type="button" class="btn btn-primary">Add text</button>
        <input type="submit" value="Submit">
        <button id="save-btn" type="button" class="btn btn-primary">Save results</button>
      </div>
    </form>
    <div class="main-div" style="font-family: Arial, sans-serif;">
      <h3>Solution</h3>
      <p><strong>Nb_texts</strong> :<span id="nb_texts"></span></p>
      <p><strong>Match</strong> :<span id="match"></span></p>
      <p><strong>Laps Time</strong> :<span id="laps"></span></p>
      <strong><p>Combined text</p></strong>
      <div class="div-res" style="background-color: #f2f2f2;">
        <div><span id="result"></span></div>
      </div>
      
    </div>

  </div>
</body>

<script>
  // Get the div where the new inputs will be added
  var inputsDiv = document.getElementById('my-inputs');

  // Get the add button
  var addBtn = document.getElementById('add-btn');

  // Add a click event listener to the add button
  addBtn.addEventListener('click', function() {
    // Counter to keep track of the number of new inputs
    var inputCounter = inputsDiv.children.length;
    
    // Create a new div element
    var newDiv = document.createElement('div');

    // Create a new label element
    var newLabel = document.createElement('label');
    newLabel.setAttribute('for', 'text' + inputCounter);
    newLabel.textContent = 'Text ' + (inputCounter + 1);

    // Create a new input element
    var newInput = document.createElement('input');
    newInput.setAttribute('type', 'text');
    newInput.setAttribute('class', 'form-control');
    newInput.setAttribute('name', 'text' + inputCounter);
    newInput.setAttribute('id', 'text' + inputCounter);
    newInput.setAttribute('placeholder', 'text...');

    // Add the label and input to the new div
    newDiv.appendChild(newLabel);
    newDiv.appendChild(newInput);

    // Add the new div to the inputs div
    inputsDiv.appendChild(newDiv);

    // Increment the input counter
    inputCounter++;
  });

</script>

</html>
