 function submitForm(userType) {
        var form = document.createElement('form');
        form.method = 'post';
        form.action = '/registration';

        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'userType';
        input.value = userType;

        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();
 }