# Test Baseline Comparison Guide

This project uses test baselines to track regressions across branches and changes. Tests are integration tests that hit real Belgian eHealth acceptance environments, so some tests may fail due to external service state rather than code issues. Baselines let us distinguish real regressions from pre-existing failures.

## Baseline files

Baselines are stored as `test-baseline-*.txt` files in the project root. Each file follows this format:

```
# Test Baseline - branch: <branch-name>
# Commit: <short-hash>
# Date: <ISO-8601>
# Total: N | Pass: N | Fail: N | Error: N | Skip: N
#
PASS   | org.taktik.freehealth.middleware.web.controllers.SomeTest.someMethod
FAIL   | org.taktik.freehealth.middleware.web.controllers.OtherTest.otherMethod
```

Key baselines:
- `test-baseline-master.txt` — reference baseline from the master branch
- `test-baseline-after-fixes3.txt` — baseline after Spring Boot 3.5.5 migration fixes

## Running the full test suite

```bash
JAVA_HOME=/Users/aduchate/.sdkman/candidates/java/21.0.1-tem ./gradlew test --continue
```

This takes approximately 30-60 minutes. The `--continue` flag ensures all tests run even if some fail.

Requires JDK 21 and a `test/resources/test.properties` file with real eHealth keystores and credentials.

## Generating a new baseline from test results

After running the full test suite, parse the JUnit XML results into baseline format:

```bash
for xml in build/test-results/test/*.xml; do
  python3 -c "
import xml.etree.ElementTree as ET
tree = ET.parse('$xml')
root = tree.getroot()
for tc in root.findall('.//testcase'):
    cls = tc.get('classname','')
    name = tc.get('name','')
    if tc.find('failure') is not None or tc.find('error') is not None:
        print(f'FAIL   | {cls}.{name}')
    elif tc.find('skipped') is not None:
        print(f'SKIP   | {cls}.{name}')
    else:
        print(f'PASS   | {cls}.{name}')
" 2>/dev/null
done | sort -t'|' -k2 > /tmp/test-baseline-new.txt
```

Then add a header with metadata:

```bash
BRANCH=$(git rev-parse --abbrev-ref HEAD)
COMMIT=$(git rev-parse --short HEAD)
DATE=$(date -u +%Y-%m-%dT%H:%M:%SZ)
TOTAL=$(wc -l < /tmp/test-baseline-new.txt | tr -d ' ')
PASS=$(grep -c "^PASS" /tmp/test-baseline-new.txt)
FAIL=$(grep -c "^FAIL" /tmp/test-baseline-new.txt)

{
  echo "# Test Baseline - branch: $BRANCH"
  echo "# Commit: $COMMIT"
  echo "# Date: $DATE"
  echo "# Total: $TOTAL | Pass: $PASS | Fail: $FAIL | Error: 0 | Skip: 0"
  echo "#"
  cat /tmp/test-baseline-new.txt
} > test-baseline-<descriptive-name>.txt
```

## Comparing results against a baseline

### Quick summary

```bash
echo "=== Current ==="
grep -c "^PASS" /tmp/test-baseline-new.txt
grep -c "^FAIL" /tmp/test-baseline-new.txt

echo "=== Baseline ==="
grep -c "^PASS" test-baseline-master.txt
grep -c "^FAIL" test-baseline-master.txt
```

### Find regressions (PASS on baseline, FAIL now)

```bash
diff \
  <(grep "^PASS" test-baseline-master.txt | sort -t'|' -k2) \
  <(grep "^PASS" /tmp/test-baseline-new.txt | sort -t'|' -k2) \
  | grep "^<" | sed 's/^< //'
```

Each line printed is a test that **was passing** in the baseline but **is now failing** — a regression.

### Find improvements (FAIL on baseline, PASS now)

```bash
diff \
  <(grep "^FAIL" test-baseline-master.txt | sort -t'|' -k2) \
  <(grep "^FAIL" /tmp/test-baseline-new.txt | sort -t'|' -k2) \
  | grep "^<" | sed 's/^< //'
```

Each line printed is a test that **was failing** in the baseline but **is now passing** — an improvement.

### Full comparison one-liner

```bash
BASELINE=test-baseline-master.txt
CURRENT=/tmp/test-baseline-new.txt

echo "=== Regressions (PASS → FAIL) ===" && \
diff <(grep "^PASS" $BASELINE | sort -t'|' -k2) <(grep "^PASS" $CURRENT | sort -t'|' -k2) | grep "^<" | sed 's/^< //' && \
echo "" && \
echo "=== Improvements (FAIL → PASS) ===" && \
diff <(grep "^FAIL" $BASELINE | sort -t'|' -k2) <(grep "^FAIL" $CURRENT | sort -t'|' -k2) | grep "^<" | sed 's/^< //'
```

## Running a subset of tests

To re-run specific failing tests for investigation:

```bash
# Single test class
./gradlew test --tests "org.taktik.freehealth.middleware.web.controllers.ConsentControllerTest"

# Single test method
./gradlew test --tests "org.taktik.freehealth.middleware.web.controllers.ConsentControllerTest.getConsent"

# Multiple tests
./gradlew test \
  --tests "org.taktik.freehealth.middleware.web.controllers.ConsentControllerTest.getConsent" \
  --tests "org.taktik.freehealth.middleware.web.controllers.TherLinkControllerTest.doesTherLinkExist" \
  --continue
```

## Interpreting results

- **Regressions (PASS → FAIL)** are the primary concern. These indicate code changes broke something.
- **Improvements (FAIL → PASS)** confirm that fixes worked.
- **New tests** (present in current but not in baseline) won't appear in the diff — compare total counts to detect them.
- **Persistent failures** (FAIL in both) are usually caused by external eHealth service state, expired test certificates, or pre-existing bugs unrelated to your changes.
- **Flaky tests** may flip between runs due to external service timeouts or transient network issues. If a regression appears in one run but not another, re-run that specific test to confirm.
