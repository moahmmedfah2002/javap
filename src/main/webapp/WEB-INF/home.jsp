<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

</head>

<body>
<style>
    body{
        /*background-image: url('hi.png');*/
        background-repeat: no-repeat;
        overflow-x: hidden;
    }
    .dot {
        height: 1400px;
        width: 2000px;
        position: absolute;
        z-index: -1;
        background-color: #4a4a8a49;
        border-radius: 15%;
        display: inline-block;
        margin-left: 450px;
        margin-top: -620px;
    }
    #log{
        z-index: 1;
        margin-top: 5%;
        border: none;
        border-radius: 10px;
        width:50%;
        height: 500px;
        padding: 10%;
        background-color:  #08080833;
        box-shadow: rgba(53, 8, 126, 0.469) 20px 20px;
    }
    #formulaire{

        display: flex;
        text-align: center;

    }
    .label{
        font-size: xx-large;
        color: rgb(49, 6, 117);
        font-family: Georgia, 'Times New Roman', Times, serif;
    }
    .input{
        background-color: rgb(49, 6, 117);
        border: none;
        border-radius: 10px;
        margin: 5%;
        width: 100%;
        color: aliceblue;
        font-size: x-large;
        font-family: Georgia, 'Times New Roman', Times, serif;

    }
    .col{
        width: 100px;
        text-align: left;
    }
    #login{
        margin-top: 10%;
        background-color: rgb(55, 0, 255);
        font-family: Georgia, 'Times New Roman', Times, serif;
    }
    #title{
        text-align: center;
        margin-top: 100px;
        color: rgb(4, 6, 121);
        font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif ;
    }

</style>
<span class="dot"></span>
<h1 id="title">
    MyCDM
</h1>

<div class="container-md" id="log">
    <div id="formulaire" class="row justify-content-md-center">
        <form action="affiche" method="post">
            <div class="row">
                <div class="col-sm">
                    <%--@declare id="email"--%><label class="label" for="email">Email</label>
                </div>
                <div class="col-sm">
                    <input class="input" type="text" name="emailF"><br>
                </div>
            </div>
            <div class="row">
                <div class="col-sm">
                    <%--@declare id="password"--%><label class="label" for="password">Password</label>
                </div>
                <div class="col-sm">
                    <input class="input" type="password" name="passF"><br>
                </div>
            </div>
            <input id="login" class="btn btn-primary" type="submit" value="Login">

        </form>

    </div>
</div>

</body>
</html>