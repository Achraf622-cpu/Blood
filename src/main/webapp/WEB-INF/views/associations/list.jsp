    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Associations • Donner</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <script>
          tailwind.config = {
            theme: {
              extend: {
                colors: {
                  primary: {50:'#eff6ff',100:'#dbeafe',200:'#bfdbfe',300:'#93c5fd',400:'#60a5fa',500:'#3b82f6',600:'#2563eb',700:'#1d4ed8',800:'#1e40af',900:'#1e3a8a'}
                }
              }
            }
          }
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
        <div class="max-w-6xl mx-auto px-4 py-8">
            <div class="flex items-center justify-between mb-6">
                <h1 class="text-2xl font-bold">Associations</h1>
                <a href="${pageContext.request.contextPath}/association?action=add" class="px-4 py-2 bg-primary-600 text-white rounded-md hover:bg-primary-700">Add Association</a>
            </div>

            <div class="mb-4 flex gap-2 text-sm">
                <a href="${pageContext.request.contextPath}/" class="text-gray-600 hover:text-gray-900">Home</a>
                <span>/</span>
                <span class="text-gray-900">Associations</span>
            </div>

            <c:choose>
                <c:when test="${empty associations}">
                    <div class="bg-white rounded-lg shadow p-8 text-center">
                        <p class="text-gray-600">No associations yet.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="bg-white rounded-lg shadow overflow-hidden">
                        <div class="overflow-x-auto">
                            <table class="min-w-full text-sm">
                                <thead class="bg-gray-100 text-gray-700">
                                    <tr>
                                        <th class="px-4 py-3 text-left">ID</th>
                                        <th class="px-4 py-3 text-left">Donor ID</th>
                                        <th class="px-4 py-3 text-left">Recipient ID</th>
                                        <th class="px-4 py-3 text-left">Date</th>
                                        <th class="px-4 py-3 text-left">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="association" items="${associations}">
                                        <tr class="border-t">
                                            <td class="px-4 py-3">${association.id}</td>
                                            <td class="px-4 py-3">${association.donneurId}</td>
                                            <td class="px-4 py-3">${association.receveurId}</td>
                                            <td class="px-4 py-3">${association.dateAssociation}</td>
                                            <td class="px-4 py-3">
                                                <a href="${pageContext.request.contextPath}/association?action=delete&id=${association.id}" class="text-red-600 hover:underline" onclick="return confirm('Are you sure?')">Delete</a>
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