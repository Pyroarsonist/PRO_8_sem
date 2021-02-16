import matplotlib.pyplot as plt
import pandas as pd

df = pd.DataFrame(pd.read_json("../data.json"))

ax = plt.gca()
df.plot(x='size', y='tapeDuration', kind='line', ax=ax)
df.plot(x='size', y='foxDuration', kind='line', ax=ax)
plt.legend(["Tape duration", "Fox duration"])
plt.show()
