function validateAndSubmit() {
            var startDate = new Date(document.getElementById('startDate').value);
            var endDate = new Date(document.getElementById('endDate').value);

            if (startDate > endDate) {
                alert('StartDate cannot > EndDate');
            } else if (startDate <= endDate) {
                document.getElementById('dateForm').submit();
            }
        }