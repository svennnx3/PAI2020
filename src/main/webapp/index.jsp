<html>
<body>
<h2> Kalkulator</h2>
<form action="CalcServlet" method="POST">
    <input type="number" name="number1">
    <select name="operations">
        <option name="+" value="+">+</option>
        <option name="-" value="-">-</option>
        <option name="*" value="*">*</option>
        <option nam="/" value="/">/</option>
    </select>
    <input type="number" name="number2">
    <button type="submit" name="calculate">=</button>
</form>
</body>
</html>
