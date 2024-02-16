<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Biblioteca</title>
                <style>
                    body {
                        font-family: 'Arial', sans-serif;
                    }
                    h1 {
                        color: #333;
                    }
                    h2 {
                        background-color: #4CAF50;
                        color: white;
                        padding: 10px;
                        border-radius: 5px;
                    }
                    table {
                        width: 100%;
                        border-collapse: collapse;
                        margin-top: 10px;
                    }
                    th, td {
                        border: 2px solid #ddd;
                        padding: 8px;
                        text-align: left;
                    }
                    th {
                        background-color: #4CAF50;
                        color: white;
                    }
                    tr:nth-child(even) {
                        background-color: #f2f2f2;
                    }
                </style>
            </head>
            <body>
                <h1>Mi Biblioteca</h1>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="Categoria">
        <h2>
            <xsl:value-of select="nombre"/>
        </h2>
        <table>
            <tr>
                <th>Titulo</th>
                <th>Autor</th>
                <th>Editorial</th>
                <th>Fecha de Publicacion</th>
                <th>ISBN</th>
                <th>Numero de Paginas</th>
                <th>Precio</th>
                <th>Sinopsis</th>
            </tr>
            <xsl:apply-templates select="Libro"/>
        </table>
    </xsl:template>

    <xsl:template match="Libro">
        <tr>
            <td><xsl:value-of select="Titulo"/></td>
            <td><xsl:value-of select="Autor"/></td>
            <td><xsl:value-of select="Editorial"/></td>
            <td><xsl:value-of select="FechaDePublicacion"/></td>
            <td><xsl:value-of select="Isbn"/></td>
            <td><xsl:value-of select="NumeroPaginas"/></td>
            <td><xsl:value-of select="Precio"/></td>
            <td><xsl:value-of select="Sinopsis"/></td>
        </tr>
    </xsl:template>

</xsl:stylesheet>