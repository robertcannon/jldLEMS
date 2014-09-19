import os
import os.path
import sys

jlemsdev_src_dir = 'src/org/lemsml/jlems/'
jlems_src_dir = '../jLEMS/src/main/java/org/lemsml/jlems/'

dirs_to_compare = ['core/type']
dirs_to_compare = ['core/run']
dirs_to_compare = ['core/expression']
dirs_to_compare = ['core/sim']
dirs_to_compare = ['core/xml']
dirs_to_compare = ['core/flatten']

                   
#dirs_to_compare = ['core/eval']
dirs_to_compare = ['core/run']

all = len(sys.argv) == 2 and sys.argv[1] == 'all'

verbose = False

examples = False

if all:
    
    dirs_to_compare = ['io',
                       'core/sim',
                       'core/expression',
                       'core/run',
                       'core/type',
                       'core/eval',
                       'core/util',
                       'core/xml',
                       'core/display',
                       'core/out',
                       'core/reader']
elif examples:
    jlemsdev_src_dir = 'examples/'
    jlems_src_dir = '../jLEMS/src/test/resources/'
    dirs_to_compare = ['.']


ignores = ['SendPort.java', 'SimulatorShortcut.java', 'InputSource.java', 'LocalParameters.java', 'ComponentRelative.java', \
           'ReceivePort.java', 'CableCell.java','LocalParameterValue']

missing_files = []
different_files = []

for dir_to_compare in dirs_to_compare:
    
    jlemsdev_dir = jlemsdev_src_dir+dir_to_compare
    jlems_dir = jlems_src_dir+dir_to_compare
    
    dirList=os.listdir(jlemsdev_dir)
    
    for fname in dirList:
        
        if fname not in ignores:

            jlemsdev_file = jlemsdev_dir+"/"+fname
            jlems_file = jlems_dir+"/"+fname

            if os.path.isfile(jlemsdev_file):
                if verbose: print("Checking: "+jlemsdev_file)
                if not os.path.isfile(jlems_file):
                    if verbose: print("Missing!")
                    missing_files.append("%s (%s)"%(jlems_file,jlemsdev_file))
                else:
                    jlemsdev = open(jlemsdev_file, 'r').read()
                    jlems = open(jlems_file, 'r').read()
                    if jlemsdev != jlems:
                        if verbose: print("Different from "+jlems_file)
                        #different_files.append("kompare "+jlems_file+" "+jlemsdev_file)
                        different_files.append("kompare "+jlemsdev_file+" "+jlems_file)
                    
    dirList=os.listdir(jlems_dir)
    
    for fname in dirList:
        jlemsdev_file = jlemsdev_dir+"/"+fname
        jlems_file = jlems_dir+"/"+fname
        
        if os.path.isfile(jlems_file):
            if verbose: print("Checking: "+jlems_file)
            if not os.path.isfile(jlemsdev_file):
                if verbose: print("Missing!")
                missing_files.append("%s (%s)"%(jlemsdev_file,jlems_file))
                
                
print("Done!\n\n")
print("++++++ Missing files (%i): +++++++"%len(missing_files))
for mf in missing_files:
    print("  -  %s"%mf)
print
print("++++++ Different files (%i): +++++++"%len(different_files))
for df in different_files:
    print("  -  %s"%df)
print
    