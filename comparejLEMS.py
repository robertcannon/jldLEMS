import os
import os.path
import shutil

jlemsdev_src_dir = 'src/org/lemsml/jlems/'
jlems_src_dir = '../jLEMS/src/main/java/org/lemsml/jlems/'

dirs_to_compare = ['core/type']
dirs_to_compare = ['core/run']
dirs_to_compare = ['core/expression']

missing_files = []
different_files = []

for dir_to_compare in dirs_to_compare:
    
    jlemsdev_dir = jlemsdev_src_dir+dir_to_compare
    jlems_dir = jlems_src_dir+dir_to_compare
    
    dirList=os.listdir(jlemsdev_dir)
    
    for fname in dirList:
        
        jlemsdev_file = jlemsdev_dir+"/"+fname
        jlems_file = jlems_dir+"/"+fname
        
        if os.path.isfile(jlemsdev_file):
            print("Checking: "+jlemsdev_file)
            if not os.path.isfile(jlems_file):
                print("Missing!")
                missing_files.append(jlems_file)
            else:
                jlemsdev = open(jlemsdev_file, 'r').read()
                jlems = open(jlems_file, 'r').read()
                if jlemsdev != jlems:
                    print("Different from "+jlems_file)
                    different_files.append(jlems_file+" "+jlemsdev_file)
                
                
print("Done!\n\n")
print("++++++ Missing files: +++++++")
for mf in missing_files:
    print("  -  %s"%mf)
print("++++++ Different files: +++++++")
for df in different_files:
    print("  -  %s"%df)
    