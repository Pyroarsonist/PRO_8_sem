import matplotlib.pyplot as plt
import pandas as pd

df = pd.DataFrame(pd.read_json("../proc_data.json"))

ax = plt.gca()
df.plot(x='poolSize', y='tapeDuration', kind='line', ax=ax)
df.plot(x='poolSize', y='foxDuration', kind='line', ax=ax)
plt.legend(["Tape duration", "Fox duration"])
plt.show()
