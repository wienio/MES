import matplotlib.pyplot as plt
import numpy as np

text_file = open("../wyniki/result-step3780.0[s].txt", "r")

step = ''
x = np.zeros(shape =(31, 31), dtype=np.float64)
counter = 0
for line in text_file:
    if line.startswith("Step"):
        step = line
        step = step.replace('Step', 'kroku')
    elif not line == '\n':
        alist = []
        alist.extend([float(i) for i in line.split()])
        print(alist[0])
        for i in range (0, 31):
            x[counter, i] = alist[i]
        counter = counter + 1

min = 100
max = -100
fig, ax = plt.subplots()
cax = ax.imshow(x, interpolation='nearest', cmap='afmhot')
ax.set_title('Wykres zmiany temperatury w siatce dla ' + step + ' [s]')


cbar = fig.colorbar(cax, ticks=[np.amin(x) + 0.5, (np.amax(x) + np.amin(x))/2 , np.amax(x) - 0.5], orientation = 'horizontal')
cbar.ax.set_yticklabels(['< -16', '0', '> 14'])


plt.imshow(x, cmap='afmhot', interpolation='nearest')
plt.show()
