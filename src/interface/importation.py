from datetime import date
import json
import pymysql
import sys

today = str(date.today()) + ".json"

print("Today's date:", today)

json_data = open(today).read()
json_obj = json.loads(json_data)
con = pymysql.connect(host="localhost", user="root", password="", db="json")

cursor = con.cursor()

for item in json_obj:
    person = item.get("person")
    year = item.get("year")
    company = item.get("company")
    cursor.execute("insert into json_file(person,year,company) value(%s,%s,%s)", (person, year, company))
con.commit()
con.close()

print("importe avec succes")

sys.exit()
