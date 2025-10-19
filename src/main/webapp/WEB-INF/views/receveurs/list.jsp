    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Recipients • Donner</title>
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

        <div class="max-w-7xl mx-auto px-4 py-8">
            <div class="flex items-center justify-between mb-6">
                <div>
                    <h1 class="text-2xl font-semibold text-gray-900">Recipients</h1>
                    <p class="text-gray-600 text-sm">Manage all recipients in the system</p>
                </div>
                <a href="${pageContext.request.contextPath}/receveur?action=add" class="inline-flex items-center px-4 py-2 rounded-md bg-primary-600 text-white hover:bg-primary-700">Add new recipient</a>
            </div>

        <c:choose>
            <c:when test="${empty receveurs}">
                <div class="bg-white rounded-lg border shadow-sm p-8 text-center text-gray-600">
                    No recipients yet. <a class="text-primary-700 hover:underline" href="${pageContext.request.contextPath}/receveur?action=add">Add the first recipient</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="bg-white rounded-lg shadow overflow-hidden">
                    <div class="overflow-x-auto">
                        <table class="min-w-full text-sm">
                            <thead class="bg-gray-100 text-gray-700">
                                <tr>
                                    <th class="px-4 py-3 text-left">ID</th>
                                    <th class="px-4 py-3 text-left">Name</th>
                                    <th class="px-4 py-3 text-left">Phone</th>
                                    <th class="px-4 py-3 text-left">CIN</th>
                                    <th class="px-4 py-3 text-left">Birth Date</th>
                                    <th class="px-4 py-3 text-left">Gender</th>
                                    <th class="px-4 py-3 text-left">Blood Group</th>
                                    <th class="px-4 py-3 text-left">Medical Situation</th>
                                    <th class="px-4 py-3 text-left">Status</th>
                                    <th class="px-4 py-3 text-left">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="receveur" items="${receveurs}">
                                    <tr class="border-t">
                                        <td class="px-4 py-3">${receveur.id}</td>
                                        <td class="px-4 py-3">${receveur.nom} ${receveur.prenom}</td>
                                        <td class="px-4 py-3">${receveur.telephone}</td>
                                        <td class="px-4 py-3">${receveur.cin}</td>
                                        <td class="px-4 py-3">${receveur.dateNaissance}</td>
                                        <td class="px-4 py-3">${receveur.sexe}</td>
                                        <td class="px-4 py-3">${receveur.groupeSanguin}</td>
                                        <td class="px-4 py-3">${receveur.situationMedicale}</td>
                                        <td class="px-4 py-3">${receveur.etatReceveur}</td>
                                        <td class="px-4 py-3">
                                            <a href="${pageContext.request.contextPath}/receveur?action=edit&id=${receveur.id}" class="text-primary-700 hover:underline">Edit</a>
                                            <span class="mx-2 text-gray-300">|</span>
                                            <a href="${pageContext.request.contextPath}/receveur?action=delete&id=${receveur.id}" class="text-red-600 hover:underline" onclick="return confirm('Are you sure?')">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
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