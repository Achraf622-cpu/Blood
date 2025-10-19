<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Recipient • Donner</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
      tailwind.config = { theme: { extend: { colors: { primary: {50:'#eff6ff',100:'#dbeafe',200:'#bfdbfe',300:'#93c5fd',400:'#60a5fa',500:'#3b82f6',600:'#2563eb',700:'#1d4ed8',800:'#1e40af',900:'#1e3a8a'} } } } }
    </script>
</head>
<body class="bg-gray-50 min-h-screen">
    <header class="border-b bg-white/70 backdrop-blur supports-[backdrop-filter]:bg-white/60">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div class="flex h-16 items-center justify-between">
                <a href="${pageContext.request.contextPath}/" class="flex items-center gap-2">
                    <span class="inline-flex h-9 w-9 items-center justify-center rounded-md bg-primary-600 text-white font-bold">BD</span>
                    <span class="text-lg font-semibold text-gray-900">Donner</span>
                </a>
                <nav class="flex items-center gap-2">
                    <a href="${pageContext.request.contextPath}/donneur?action=list" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-100">Donors</a>
                    <a href="${pageContext.request.contextPath}/receveur?action=list" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-100">Recipients</a>
                    <a href="${pageContext.request.contextPath}/association?action=list" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-100">Associations</a>
                </nav>
            </div>
        </div>
    </header>

    <div class="max-w-3xl mx-auto px-4 sm:px-6 lg:px-8 py-10">
        <div class="mb-6">
            <nav class="text-sm text-gray-600 flex items-center gap-2">
                <a href="${pageContext.request.contextPath}/" class="hover:text-gray-900">Home</a>
                <span>/</span>
                <a href="${pageContext.request.contextPath}/receveur?action=list" class="hover:text-gray-900">Recipients</a>
                <span>/</span>
                <span class="text-gray-900">Add</span>
            </nav>
            <h1 class="mt-2 text-2xl font-semibold text-gray-900">Add new recipient</h1>
            <p class="mt-1 text-gray-600">Add a recipient who needs blood.
            </p>
        </div>

        <form method="post" action="${pageContext.request.contextPath}/receveur" class="bg-white rounded-xl border shadow-sm p-6">
            <input type="hidden" name="action" value="add">

            <div class="grid grid-cols-1 sm:grid-cols-2 gap-6">
                <div>
                    <label for="nom" class="block text-sm font-medium text-gray-700">First name *</label>
                    <input type="text" id="nom" name="nom" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500">
                </div>
                <div>
                    <label for="prenom" class="block text-sm font-medium text-gray-700">Last name *</label>
                    <input type="text" id="prenom" name="prenom" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500">
                </div>

                <div>
                    <label for="telephone" class="block text-sm font-medium text-gray-700">Phone *</label>
                    <input type="text" id="telephone" name="telephone" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500">
                </div>
                <div>
                    <label for="cin" class="block text-sm font-medium text-gray-700">CIN *</label>
                    <input type="text" id="cin" name="cin" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500">
                </div>

                <div>
                    <label for="dateNaissance" class="block text-sm font-medium text-gray-700">Birth date *</label>
                    <input type="date" id="dateNaissance" name="dateNaissance" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500">
                </div>
                <div>
                    <label for="sexe" class="block text-sm font-medium text-gray-700">Gender *</label>
                    <select id="sexe" name="sexe" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500">
                        <option value="">Select...</option>
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                    </select>
                </div>

                <div>
                    <label for="groupeSanguin" class="block text-sm font-medium text-gray-700">Blood group *</label>
                    <select id="groupeSanguin" name="groupeSanguin" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500">
                        <option value="">Select...</option>
                        <option value="A+">A+</option>
                        <option value="A-">A-</option>
                        <option value="B+">B+</option>
                        <option value="B-">B-</option>
                        <option value="AB+">AB+</option>
                        <option value="AB-">AB-</option>
                        <option value="O+">O+</option>
                        <option value="O-">O-</option>
                    </select>
                </div>
                <div>
                    <label for="situationMedicale" class="block text-sm font-medium text-gray-700">Medical situation *</label>
                    <select id="situationMedicale" name="situationMedicale" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500">
                        <option value="NORMAL">Normal</option>
                        <option value="URGENT">Urgent</option>
                        <option value="CRITIQUE">Critical</option>
                    </select>
                </div>
            </div>

            <div class="mt-8 flex items-center justify-end gap-3">
                <a href="${pageContext.request.contextPath}/receveur?action=list" class="inline-flex items-center px-4 py-2 rounded-md border border-gray-200 text-gray-700 hover:bg-gray-50">Cancel</a>
                <button type="submit" class="inline-flex items-center px-5 py-2.5 rounded-md bg-primary-600 text-white hover:bg-primary-700">Save recipient</button>
            </div>
        </form>
    </div>

    <footer class="mt-12 border-t bg-white/60">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6 text-sm text-gray-500 flex items-center justify-between">
            <p>&copy; <%= java.time.Year.now() %> Donner. All rights reserved.</p>
            <div class="flex gap-4">
                <a href="${pageContext.request.contextPath}/" class="hover:text-gray-700">Home</a>
                <a href="${pageContext.request.contextPath}/donneur?action=list" class="hover:text-gray-700">Donors</a>
                <a href="${pageContext.request.contextPath}/receveur?action=list" class="hover:text-gray-700">Recipients</a>
            </div>
        </div>
    </footer>
</body>
</html>

