from shutil import copyfile
import os

def write_to_file(file_name, content):
    with open(file_name, "a") as output:
        output.write(content)

def reset_file_to_empty(file_name):
    with open(file_name, "w") as file:
        file.seek(0, os.SEEK_END)
        if file.tell > 0:
            file.write("")

def clean_line(line):
    cleanline = []
    todelete = False
    for c in line:
        if c == '(' and (cleanline[len(cleanline) - 1] == "t" or cleanline[len(cleanline) - 1] =="r"):
            todelete = True
        if not todelete:
            cleanline.append(c)
        if c == ')':
            todelete = False
    return "".join(cleanline)



copyfile("omeka.sql", "omeka_copy.sql")
reset_file_to_empty("omeka_to_sqlite.sql")
with open("omeka_copy.sql") as f:
    for line in f:
        if (len(line)  < 200):
            new_line = line
            new_line = clean_line(new_line)

            new_line = new_line.replace("varchar", "TEXT")
            new_line = new_line.replace("character ", "TEXT")
            new_line = new_line.replace("clob", "TEXT")
            new_line = new_line.replace("tinytext", "TEXT")

            new_line = new_line.replace("tinyint", "INTEGER")
            new_line = new_line.replace("smallint", "INTEGER")
            new_line = new_line.replace("unsigned", "")
            new_line = new_line.replace("int ", "INTEGER")
            new_line = new_line.replace("`id`", "`_id`")
            new_line = new_line.replace("timestamp", "DATETIME")
            new_line = new_line.replace("text ", "TEXT ")
            if "ENGINE" in new_line:
                new_line = ")"
            if "LOCK" in new_line:
                new_line = ""

            # if "datetime" in new_line:
            #     new_line = ""

            write_to_file("omeka_to_sqlite.sql", new_line)
        else:
            write_to_file("omeka_to_sqlite.sql", line)

