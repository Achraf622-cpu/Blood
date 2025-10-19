<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Donner • Blood Donation Management</title>
    <meta name="description" content="Manage donors, recipients, and associations with a clean modern UI.">
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
    <link rel="icon" href="data:,">
    <meta name="color-scheme" content="light">
    <meta name="theme-color" content="#ffffff">
    <style>
      html { scroll-behavior: smooth; }
    </style>
    
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

    <main class="relative isolate">
        <section class="bg-gradient-to-b from-primary-50 to-white">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
                <div class="grid lg:grid-cols-2 gap-10 items-center">
                    <div>
                        <h1 class="text-4xl sm:text-5xl font-semibold tracking-tight text-gray-900">Blood Donation Management</h1>
                        <p class="mt-4 text-lg text-gray-600">A streamlined way to manage donors, recipients, and compatibility associations.</p>
                        <div class="mt-8 flex flex-wrap gap-3">
                            <a href="${pageContext.request.contextPath}/donneur?action=list" class="inline-flex items-center px-5 py-3 rounded-md bg-primary-600 text-white hover:bg-primary-700">Manage Donors</a>
                            <a href="${pageContext.request.contextPath}/receveur?action=list" class="inline-flex items-center px-5 py-3 rounded-md bg-gray-900 text-white hover:bg-gray-800">Manage Recipients</a>
                            <a href="${pageContext.request.contextPath}/association?action=list" class="inline-flex items-center px-5 py-3 rounded-md bg-white text-gray-900 ring-1 ring-inset ring-gray-200 hover:bg-gray-50">View Associations</a>
                        </div>
                    </div>
                    <div class="relative">
                        <div class="rounded-2xl border bg-white shadow-sm p-6">
                            <h2 class="text-base font-semibold text-gray-900">Quick actions</h2>
                            <div class="mt-4 grid sm:grid-cols-2 gap-3">
                                <a href="${pageContext.request.contextPath}/donneur?action=add" class="flex items-center justify-center rounded-md border border-gray-200 px-4 py-3 text-sm font-medium text-gray-700 hover:bg-gray-50">Add new donor</a>
                                <a href="${pageContext.request.contextPath}/receveur?action=add" class="flex items-center justify-center rounded-md border border-gray-200 px-4 py-3 text-sm font-medium text-gray-700 hover:bg-gray-50">Add new recipient</a>
    </div>
                            <ul class="mt-6 list-disc list-inside text-sm text-gray-600 space-y-1">
                                <li>Maintain accurate donor data</li>
                                <li>Track recipient needs</li>
                                <li>Build compatibility associations</li>
        </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

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
