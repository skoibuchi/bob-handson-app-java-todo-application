// フラッシュメッセージの自動非表示
document.addEventListener('DOMContentLoaded', function() {
    const alerts = document.querySelectorAll('.alert-dismissible');
    alerts.forEach(alert => {
        setTimeout(() => {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        }, 5000);
    });
});

// 削除確認ダイアログ
function confirmDelete(taskTitle) {
    return confirm(`タスク「${taskTitle}」を削除してもよろしいですか？`);
}

// Made with Bob
