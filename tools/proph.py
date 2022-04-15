class List(list):
  @property
  def length(self):
    return len(self)

  def map(self, func):
    return List(map(func, self))

  def filter(self, func=lambda x: x):
    return List(filter(func, self))

  def every(self, func):
    for i in self:
      if not func(i):
        return False
    return True

  def items(self):
    return List(enumerate(self))

  def join(self, separator: str):
    return separator.join(self.map(str))

  def sort(self, key=None, reverse=None):
    return List(sorted(self, key=key, reverse=reverse))

DEBUG = False

def debug(*args):
  if DEBUG:
    print('[DEBUG]', *args)
